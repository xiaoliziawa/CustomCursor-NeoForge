package fr.atesab.customcursormod.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.system.MemoryUtil;

import fr.atesab.customcursormod.common.config.Configuration;
import fr.atesab.customcursormod.common.config.CursorConfig;
import fr.atesab.customcursormod.common.cursor.CursorClick;
import fr.atesab.customcursormod.common.cursor.CursorType;
import fr.atesab.customcursormod.common.gui.GuiWaiter;
import fr.atesab.customcursormod.common.handler.GameType;

public class CursorMod {
	private static CursorMod instance;
	public static final String MOD_ID = "customcursor";
	public static final String MOD_NAME = "Custom Cursor Mod";
	public static final String MOD_VERSION = "1.3.4";
	public static final String MOD_AUTHORS = "ATE47, KevinFernandezDominguez";
	public static final String MOD_LICENCE = "GNU GPL 3";
	public static final Logger logger = Logger.getLogger(MOD_NAME);
	public final GuiWaiter waiter = new GuiWaiter();
	private boolean forceNextCursor;
	private CursorType currentCursorType;
	private Map<CursorType, CursorConfig> cursors;
	private List<CursorClick> cursorClicks;
	private Configuration config;
	private GameType type;
	private long windowHandle;

	/**
	 * @return the instance
	 */
	public static CursorMod getInstance() {
		return instance;
	}

	public CursorMod(GameType type) {
		instance = this;
		this.type = type;
		this.forceNextCursor = false;
		this.currentCursorType = CursorType.POINTER;
		this.cursors = new HashMap<>();
		this.cursorClicks = new ArrayList<>();
		this.config = new Configuration();
		registerCursor(currentCursorType, CursorType.HAND, CursorType.HAND_GRAB, CursorType.BEAM, CursorType.CROSS);
	}

	/**
	 * @return the type
	 */
	public GameType getType() {
		return type;
	}

	public void loadData(long windowHandle) {
		this.windowHandle = windowHandle;
	}

	public void changeCursor(CursorType cursor) {
		changeCursor(cursor, forceNextCursor);
		forceNextCursor = false;
	}

	private void changeCursor(CursorType cursor, boolean forceChange) {
		if (!forceChange && cursor == currentCursorType)
			return;
		currentCursorType = cursor;
		applyCursor();
	}

	/**
	 * 强制应用当前光标（即使类型没有改变）
	 * 用于防止 Minecraft 重置光标为系统默认光标
	 */
	public void applyCursor() {
		// 检查是否已经初始化
		if (windowHandle == 0 || cursors == null || currentCursorType == null) {
			return;
		}

		try {
			CursorConfig cursorConfig = cursors.getOrDefault(currentCursorType, currentCursorType.getDefaultConfig());
			long cursorPtr = cursorConfig.getCursor();
			if (cursorPtr != MemoryUtil.NULL) {
				GLFW.glfwSetCursor(windowHandle, cursorPtr);
			}
		} catch (Exception e) {
			// 静默处理异常，避免在初始化阶段崩溃
		}
	}

	/**
	 * Force the next cursor to change (if config were change for example)
	 */
	public void forceNextCursor() {
		forceNextCursor = config.dynamicCursor;
	}

	/**
	 * @return the configuration
	 */
	public Configuration getConfig() {
		return config;
	}

	/**
	 * @return the map of all {@link CursorConfig} by there {@link CursorType}
	 */
	public Map<CursorType, CursorConfig> getCursors() {
		return cursors;
	}

	/**
	 * Register new {@link CursorType} (Work only before FML Post Initialization)
	 * 
	 * @param cursorTypes the cursors
	 */
	public void registerCursor(CursorType... cursorTypes) {
		for (CursorType cursorType : cursorTypes)
			cursors.put(cursorType, cursorType.getDefaultConfig());
	}

	/**
	 * replace a {@link CursorType} config
	 * 
	 * @param type   the cursor type
	 * @param config the new cursor config
	 */
	public void replaceCursor(CursorType type, CursorConfig config) {
		CursorConfig old = cursors.put(type, config);
		if (type == currentCursorType)
			changeCursor(type, true);
		if (old.isAllocate())
			old.freeCursor();
	}

	/**
	 * Save mod config
	 */
	public void saveConfig() {
		config.save();
	}

	/**
	 * @return the cursorClicks
	 */
	public List<CursorClick> getCursorClicks() {
		return cursorClicks;
	}
}
