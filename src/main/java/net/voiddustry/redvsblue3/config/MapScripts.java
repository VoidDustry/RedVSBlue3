package net.voiddustry.redvsblue3.config;

import mindustry.Vars;
import net.voiddustry.redvsblue3.game.domain.MapScriptData;
import net.voiddustry.redvsblue3.utils.Log;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class MapScripts {
    public static void loadScripts() {
        Log.info("[cyan]Loading Scripts...");
        Vars.maps.customMaps().forEach((map) -> {
            Log.info("Loading script for map: " + map.file.name());
            File script = new File(Vars.dataDirectory + "/maps/script/" + map.file.name().replace(".msav", ".rvsb"));

            Log.debug(script.getAbsolutePath());

            if (script.exists()) {
                try {
                    parseScript(script.getPath());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Log.info(script.getName() + " [cyan]loaded");
            } else {
                Log.err("[red]error while reading script for map " + map.file.name());
            }

        });
    }

    private static MapScriptData parseScript(String path) throws IOException {
        MapScriptData mapScriptData = null;

        BufferedReader br = new BufferedReader(new FileReader(path));
        String script;

        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            script = sb.toString();
        } finally {
            br.close();
        }

        script = script.replace(" ", "");
        String[] types = script.split("new");

        for (String object : types) {
            Map<String, String> keyAndValue = new HashMap<>();

            String[] setting = object.split("\n");
            for (String s : setting) {
                String[] split = s.split(":");

                if (split.length == 2) keyAndValue.put(split[0],split[1]);
            }

            if(keyAndValue.get("type").equals("map")) {
                String[] pos = keyAndValue.get("blue-spawn").split(",");

                int x = Integer.parseInt(pos[0]);
                int y = Integer.parseInt(pos[1]);

                mapScriptData = new MapScriptData(x, y, Boolean.getBoolean(keyAndValue.get("save-core")), keyAndValue.get("border").equals("v"), Integer.parseInt(keyAndValue.get("border-width")));

            }
        }
        return mapScriptData;
    }

    public static MapScriptData loadScript(String path) {
        try {
            return parseScript(path);
        } catch (IOException e) {
            return null;
        }
    }
}
