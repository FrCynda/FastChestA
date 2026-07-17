package foundationgames.enhancedblockentities.client.resource.template;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class TemplateLoader {
    private final Map<String, String> loadedTemplates = new HashMap<>();

    public TemplateLoader() {
    }

    public String getOrLoadRaw(String path) throws IOException {
        if (this.loadedTemplates.containsKey(path)) {
            return this.loadedTemplates.get(path);
        }

        try (var in = TemplateLoader.class.getResourceAsStream("/templates/" + path)) {
            if (in == null) {
                throw new IOException("Missing Enhanced Block Entities template: " + path);
            }

            var templateRaw = new String(in.readAllBytes(), StandardCharsets.UTF_8);
            this.loadedTemplates.put(path, templateRaw);

            return templateRaw;
        }
    }
}
