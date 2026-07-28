pluginManagement {
	repositories {
		mavenLocal()
		mavenCentral()
		gradlePluginPortal()
		maven("https://maven.fabricmc.net/") { name = "Fabric" }
		maven("https://repo.spongepowered.org/repository/maven-public/") { name = "Sponge" }
		maven("https://maven.kikugie.dev/snapshots") { name = "KikuGie Snapshots" }
		maven("https://maven.kikugie.dev/releases") { name = "KikuGie Releases" }
		exclusiveContent {
			forRepository { maven("https://api.modrinth.com/maven") { name = "Modrinth" } }
			filter { includeGroup("maven.modrinth") }
		}
	}
	includeBuild("build-logic")
}

plugins {
	id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
	id("dev.kikugie.stonecutter") version "0.9"
}

val settingsRootDir = rootDir

stonecutter {
	create(rootProject) {
		fun match(version: String, vararg loaders: String) {
			loaders.forEach { loader ->
				val buildscriptName = when {
					version.startsWith("1.") && loader == "fabric" -> "build.fabric-legacy.gradle.kts"
					else -> "build.$loader.gradle.kts"
				}

				version("$version-$loader", version).buildscript = buildscriptName
			}
		}

		fun env(variable: String): String? {
			val value = System.getenv(variable)
			if (value != null) return value

			val envFile = java.io.File(settingsRootDir, ".env")
			if (envFile.exists()) {
				val props = java.util.Properties()
				envFile.inputStream().use { props.load(it) }
				val fromFile = props.getProperty(variable)
				if (fromFile != null) return fromFile
			}

			return null
		}

		// This fork is Fabric-only: Forge/NeoForge loader branches from upstream
		// EnhancedBlockEntitiesReloaded have been removed. See plan Phase 0-3.
		if (env("GRADLE_ONLY_IMPORTANT_FABRIC") == "true") {
			// A handful of versions spanning every era, for fast local dev loops.
			match("26.3", "fabric")
			match("26.2", "fabric")
			match("1.21.11", "fabric")
			match("1.21.2", "fabric")
			match("1.21", "fabric")
		}
		else if (env("GRADLE_FULL_RANGE") == "true") {
			// Full 17-version target range (see plan Phase 1), covered by 10 actual
			// Stonecutter nodes - dot releases with no API changes publish from the
			// same node via publish.additionalVersions in that node's gradle.properties.
			match("26.3", "fabric")       // 26.3
			match("26.2", "fabric")       // 26.2
			match("26.1", "fabric")       // 26.1, 26.1.1, 26.1.2
			match("1.21.11", "fabric")    // 1.21.11
			match("1.21.9", "fabric")     // 1.21.9, 1.21.10
			match("1.21.6", "fabric")     // 1.21.6, 1.21.7, 1.21.8
			match("1.21.5", "fabric")     // 1.21.5
			match("1.21.4", "fabric")     // 1.21.4
			match("1.21.2", "fabric")     // 1.21.2, 1.21.3
			match("1.21", "fabric")       // 1.21, 1.21.1
		}
		else {
			// Phase 0 default: single version, Fabric only. Switch to
			// GRADLE_FULL_RANGE=true once the per-era work in the plan lands.
			match("26.2", "fabric")
		}

		vcsVersion = "26.2-fabric"
	}
}
