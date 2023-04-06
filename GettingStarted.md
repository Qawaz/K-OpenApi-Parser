# Getting Started with K OpenAPI Parser

## Build

The project should build fine in IntelliJ , since it uses Gradle , Open the project and you can run tests / regenerate api

### Special Considerations for Tests

If you expect to frequently run tests, you should set GITHUB_AUTH
environment variable to your GitHub `username:password`. The
`ExamplesTest` test uses the GitHub API to retrieve the latest
examples from the `OAI/OpenAPI-Specification` repo (specifically,
folder `examples/v3.0` in the `master` branch), and rate limits are
severe for unauthenticated requests.

### Regenerating Code

There's a [GenOpenAPI3](./src/main/java/com/wakaztahir/generator/GenOpenApi3.kt) Object which contains the function to run
to generate the API again using the file [types3.yaml](./src/main/java/com/wakaztahir/generator/types3.yaml)


## Learn About the APIs

See the [API Overview](API-Overview.md) for a detailed description of
the APIs offered by the parser, the serializer, and the model
representation, including reference treatments.

## A Simple Example

You can take the parser for a spin with the following simple program,
or of course explore with your own models.

The program parses each of the
[example OpenAPI 3.0 models](https://github.com/OAI/OpenAPI-Specification/tree/master/examples/v3.0)
currently available in the `OAI/OpenAPI-Specification` GitHub Repo. In
each case, if validation succeeds, a summary of all the model's paths,
operations and operation parameters is printed. Otherwise, all
validation messages are printed.

At the time of this writing, validation fails on the
`callback-example` because that example does not include the required
`openapi` and `info` properties.

```kotlin
package test;

import com.reprezen.kaizen.oasparser.OpenApi3Parser
import com.reprezen.kaizen.oasparser.model3.OpenApi3
import com.reprezen.kaizen.oasparser.model3.Parameter

object GettingStarted {

    @kotlin.Throws(Exception::class)
    @JvmStatic
    fun main(args: Array<String>) {
        for (modelName: String in mutableListOf(
            "api-with-examples", "callback-example", "link-example", "petstore",
            "petstore-expanded", "uspto"
        )) {
            val modelUri: java.net.URI = java.net.URI(
                "https://raw.githubusercontent.com/OAI/OpenAPI-Specification/master/examples/v3.0/"
                        + modelName + ".yaml"
            )
            processModel(modelUri)
        }
    }

    @kotlin.Throws(Exception::class)
    private fun processModel(modelUri: java.net.URI) {
        val model: OpenApi3 = OpenApi3Parser().parse(modelUri)
        java.lang.System.out.printf("== Model %s\n", modelUri)
        describeModel(model)
        java.lang.System.out.printf("------\n\n")
    }

    private fun describeModel(model: OpenApi3) {
        java.lang.System.out.printf("Title: %s\n", model.getInfo()?.getTitle())
        for (path: com.reprezen.kaizen.oasparser.model3.Path in model.getPaths().values) {
            java.lang.System.out.printf("Path %s:\n", com.reprezen.jsonoverlay.Overlay.of(path).pathInParent)
            for (op: com.reprezen.kaizen.oasparser.model3.Operation in path.getOperations().values) {
                java.lang.System.out.printf(
                    "  %s: [%s] %s\n", com.reprezen.jsonoverlay.Overlay.of(op).pathInParent?.uppercase(),
                    op.getOperationId(), op.getSummary()
                )
                for (param: com.reprezen.kaizen.oasparser.model3.Parameter in op.getParameters()) {
                    java.lang.System.out.printf(
                        "    %s[%s]:, %s - %s\n", param.getName(), param.getIn(), getParameterType(param),
                        param.getDescription()
                    )
                }
            }
        }
    }

    private fun getParameterType(param: Parameter): String {
        val schema = param.getSchema()
        return schema?.getType() ?: "unknown"
    }

}```
