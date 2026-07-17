package foundationgames.enhancedblockentities.client.resource;

import foundationgames.enhancedblockentities.client.resource.template.TemplateLoader;
import foundationgames.enhancedblockentities.client.resource.template.TemplateProvider;
import net.minecraft.SharedConstants;
import net.minecraft.client.renderer.texture.atlas.SpriteSource;
import net.minecraft.client.renderer.texture.atlas.sources.DirectoryLister;
import net.minecraft.client.renderer.texture.atlas.sources.SingleFile;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
//? if <= 1.21.11 {
/*import net.minecraft.server.packs.BuiltInMetadata;
*///?}
import net.minecraft.server.packs.PackLocationInfo;
import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.PackType;
//? if >= 1.21.9 {
import net.minecraft.util.InclusiveRange;
//?}
import net.minecraft.server.packs.metadata.MetadataSectionType;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.server.packs.resources.IoSupplier;
import org.jetbrains.annotations.Nullable;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class EBEPack implements PackResources {
    public static final Identifier BLOCK_ATLAS = Identifier.parse("blocks");

    private final Map<Identifier, AtlasResourceBuilder> atlases = new HashMap<>();
    private final Map<Identifier, IoSupplier<InputStream>> resources = new HashMap<>();
    private final Set<String> namespaces = new HashSet<>();

    private final TemplateLoader templates;

    private final PackMetadataSection packMeta;
    private final PackLocationInfo packInfo;

    public EBEPack(Identifier id, TemplateLoader templates) {
        this.templates = templates;

        //? if <= 1.21.5 {
        /*this.packMeta = new PackMetadataSection(
                Component.literal("Enhanced Block Entities Resources"),
                SharedConstants.getCurrentVersion().getPackVersion(PackType.CLIENT_RESOURCES),
                Optional.empty());
        *///?}
        //? if >= 1.21.6 < 1.21.9 {
        /*this.packMeta = new PackMetadataSection(
                Component.literal("Enhanced Block Entities Resources"),
                SharedConstants.getCurrentVersion().packVersion(PackType.CLIENT_RESOURCES),
                Optional.empty());
        *///?}
        //? if >= 1.21.9 {
        this.packMeta = new PackMetadataSection(
                Component.literal("Enhanced Block Entities Resources"),
                new InclusiveRange<>(SharedConstants.getCurrentVersion().packVersion(PackType.CLIENT_RESOURCES)));
        //?}

        this.packInfo = new PackLocationInfo(id.toString(), Component.literal(id.toString()), PackSource.BUILT_IN, Optional.empty());
    }

    public void addAtlasSprite(Identifier atlas, SpriteSource source) {
        var resource = this.atlases.computeIfAbsent(atlas, id -> new AtlasResourceBuilder());
        resource.put(source);

        this.addResource(Identifier.fromNamespaceAndPath(atlas.getNamespace(), "atlases/" + atlas.getPath() + ".json"), resource::toBytes);
    }

    public void addSingleBlockSprite(Identifier path) {
        this.addAtlasSprite(BLOCK_ATLAS, new SingleFile(path, Optional.empty()));
    }

    public void addDirBlockSprites(String dir, String prefix) {
        this.addAtlasSprite(BLOCK_ATLAS, new DirectoryLister(dir, prefix));
    }

    public void addResource(Identifier id, IoSupplier<byte[]> resource) {
        this.namespaces.add(id.getNamespace());
        this.resources.put(id, new LazyBufferedResource(resource));
    }

    public void addResource(Identifier id, byte[] resource) {
        this.namespaces.add(id.getNamespace());
        this.resources.put(id, () -> new ByteArrayInputStream(resource));
    }

    public void addPlainTextResource(Identifier id, String plainText) {
        this.addResource(id, plainText.getBytes(StandardCharsets.UTF_8));
    }

    public void addTemplateResource(Identifier id, TemplateProvider.TemplateApplyingFunction template) {
        this.addResource(id, () -> template.getAndApplyTemplate(new TemplateProvider(this.templates)).getBytes(StandardCharsets.UTF_8));
    }

    public void addTemplateResource(Identifier id, String templatePath) {
        this.addTemplateResource(id, t -> t.load(templatePath, d -> {}));
    }

    @Nullable
    @Override
    public IoSupplier<InputStream> getRootResource(String... segments) {
        return null; // Provide no root resources
    }

    @Nullable
    @Override
    public IoSupplier<InputStream> getResource(PackType type, Identifier id) {
        if (type != PackType.CLIENT_RESOURCES) return null;

        return this.resources.get(id);
    }

    @Override
    public void listResources(PackType type, String namespace, String prefix, ResourceOutput consumer) {
        if (type != PackType.CLIENT_RESOURCES) return;

        for (var entry : this.resources.entrySet()) {
            var id = entry.getKey();

            if (id.getNamespace().startsWith(namespace) && id.getPath().startsWith(prefix)) {
                consumer.accept(id, entry.getValue());
            }
        }
    }

    @Override
    public Set<String> getNamespaces(PackType type) {
        if (type != PackType.CLIENT_RESOURCES) return Set.of();

        return this.namespaces;
    }

    @Nullable
    @Override
    public <T> T getMetadataSection(MetadataSectionType<T> meta) {
        //? if <= 1.21.6 {
        /*return BuiltInMetadata.of(PackMetadataSection.TYPE, this.packMeta).get(meta);
        *///?} else {
        //? if <= 1.21.11 {
        /*return BuiltInMetadata.of(PackMetadataSection.CLIENT_TYPE, this.packMeta).get(meta);
        *///?} else {
        return PackMetadataSection.CLIENT_TYPE.withValue(this.packMeta).unwrapToType(meta).orElse(null);
        //?}
        //?}
    }

    @Override
    public PackLocationInfo location() {
        return this.packInfo;
    }

    @Override
    public void close() {
    }

    public void dump(Path dir) throws IOException {
        dir = dir.resolve("assets");

        for (var entry : this.resources.entrySet()) {
            var id = entry.getKey();
            var file = dir.resolve(id.getNamespace()).resolve(id.getPath());

            Files.createDirectories(file.getParent());

            try (var out = Files.newOutputStream(file)) {
                var in = entry.getValue().get();

                int i;
                while ((i = in.read()) >= 0) {
                    out.write(i);
                }
            }
        }
    }

    public static class PropertyBuilder {
        private Properties properties = new Properties();

        private PropertyBuilder() {}

        public PropertyBuilder def(String k, String v) {
            if (this.properties != null) {
                this.properties.setProperty(k, v);
            }

            return this;
        }

        private Properties build() {
            var properties = this.properties;
            this.properties = null;

            return properties;
        }
    }

    public static class LazyBufferedResource implements IoSupplier<InputStream> {
        private final IoSupplier<byte[]> backing;
        private byte[] buffer = null;

        public LazyBufferedResource(IoSupplier<byte[]> backing) {
            this.backing = backing;
        }

        @Override
        public InputStream get() throws IOException {
            if (buffer == null) {
                buffer = backing.get();
            }

            return new ByteArrayInputStream(buffer);
        }
    }
}
