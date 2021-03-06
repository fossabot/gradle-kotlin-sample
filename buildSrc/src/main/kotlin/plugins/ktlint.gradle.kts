/*
 * Copyright (C) 2021. Alexander Rogalskiy. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package plugins

import constants.Config
import constants.Versions
import org.jlleitschuh.gradle.ktlint.KtlintExtension
import org.jlleitschuh.gradle.ktlint.KtlintPlugin
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

apply<KtlintPlugin>()

configure<KtlintExtension> {
  version.set(Versions.ktlint)

  debug.set(true)
  verbose.set(true)
  android.set(false)
  outputToConsole.set(true)
  outputColorName.set(Config.KTLINT_COLOR_NAME)
  ignoreFailures.set(true)
  enableExperimentalRules.set(true)
  additionalEditorconfigFile.set(file("${project.rootDir}/.editorconfig"))

  reporters {
    reporter(ReporterType.PLAIN)
    reporter(ReporterType.CHECKSTYLE)
    reporter(ReporterType.JSON)
  }

  kotlinScriptAdditionalPaths {
    include(fileTree("scripts/"))
  }

  filter {
    exclude("**/generated/**")
    include("**/kotlin/**")
  }
}
