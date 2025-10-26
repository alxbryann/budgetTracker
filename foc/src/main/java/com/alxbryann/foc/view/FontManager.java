package com.alxbryann.foc.view;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.stream.Stream;

/**
 * Loads and registers custom fonts packaged under src/main/resources/fonts so
 * they can be used by name (e.g., new Font("Lexend", ...)).
 */
public final class FontManager {

    private FontManager() {}

    public static void loadAllFontsFromResources() {
        // Primary classpath folder for fonts
        loadFontsFromClasspathFolder("fonts");
    }

    private static void loadFontsFromClasspathFolder(String folder) {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        if (cl == null) {
            cl = FontManager.class.getClassLoader();
        }
        URL url = cl.getResource(folder);
        if (url == null) {
            // Folder not found on classpath; nothing to do
            return;
        }
        try {
            URI uri = url.toURI();
            if ("jar".equalsIgnoreCase(uri.getScheme())) {
                // Running from JAR: mount FS and walk
                try (FileSystem fs = FileSystems.newFileSystem(uri, Collections.emptyMap())) {
                    Path root = fs.getPath("/" + folder);
                    registerFontsUnder(root);
                }
            } else {
                // Running from exploded classes/resources
                Path root = Paths.get(uri);
                registerFontsUnder(root);
            }
        } catch (URISyntaxException | IOException e) {
            System.err.println("[FontManager] Error loading fonts: " + e.getMessage());
        }
    }

    private static void registerFontsUnder(Path root) throws IOException {
        if (root == null || !Files.exists(root)) return;
        try (Stream<Path> stream = Files.walk(root)) {
            stream.filter(p -> {
                String name = p.getFileName().toString().toLowerCase();
                return name.endsWith(".ttf") || name.endsWith(".otf");
            }).forEach(FontManager::registerFontFile);
        }
    }

    private static void registerFontFile(Path path) {
        try (InputStream in = Files.newInputStream(path)) {
            Font font = Font.createFont(Font.TRUETYPE_FONT, in);
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(font);
        } catch (Exception e) {
            // Try OTF if TTF failed
            try (InputStream in = Files.newInputStream(path)) {
                Font font = Font.createFont(Font.TYPE1_FONT, in);
                GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(font);
                System.out.println("[FontManager] Registered font (TYPE1): " + font.getFontName());
            } catch (Exception ex) {
                System.err.println("[FontManager] Failed to register font " + path + ": " + ex.getMessage());
            }
        }
    }
}
