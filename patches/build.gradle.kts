group = "app.niamwite"

patches {
    about {
        name = "Niamwite Patches"
        description = "Custom Android patches maintained by Niamwite"
        source = "https://github.com/niamwite/morphe-patches"
        author = "niamwite"
        contact = "https://github.com/niamwite/morphe-patches/issues"
        website = "https://github.com/niamwite/morphe-patches"
        license = "GPLv3"
    }
}

kotlin {
    compilerOptions {
        freeCompilerArgs.add("-Xcontext-parameters")
    }
}

dependencies {
    // Used by JsonGenerator.
    implementation(libs.gson)
}

tasks {
    register<JavaExec>("generatePatchesList") {
        description = "Build patch with patch list"

        dependsOn(build)

        classpath = sourceSets["main"].runtimeClasspath
        mainClass.set("app.morphe.util.PatchListGeneratorKt")
    }
    // Used by gradle-semantic-release-plugin.
    publish {
        dependsOn("generatePatchesList")
    }
}

kotlin {
    compilerOptions {
        freeCompilerArgs = listOf("-Xcontext-receivers")
    }
}
