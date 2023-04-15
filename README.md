# K-OpenAPI-Parser

The K OpenApi Parser is a kotlin-based
parser for OpenAPI 3.0 offering full compliance with the
[OpenAPI 3.0 Specification](https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.0.md),
and a highly uniform read/write programming API.
[OpenAPI](http://openapis.org), is the industry-standard format for machine-readable
REST API descriptions.

K-OpenAPI-Parser uses [__KATE__](https://github.com/Qawaz/KATE) , which is its own
template engine. This project is a fork

Both projects are based on `json-overlay` which is the parser generator for `yaml` / `json` based languages , This
project however makes a fork of `json-overlay`.

There are some differences between the original

- Original parser generator generates a Java parser , This
  project generates a Kotlin parser
- Original parser uses jackson for deserialization , This project 
  uses Kotlinx Serialization
- Original parser generator uses `javax.annotation.Generated` annotation ,
  This project has its own `@Generated` annotation
- Original parser generator parses the previously outputted java file and compares
  it with the new to be generated members , It doesn't modify any member functions which are user added and
  therefore do not have `@Generated` annotation on it

  This project uses [__KATE__](https://github.com/Qawaz/KATE) templates , which define what to generate and therefore
  It always overwrites the generated files , If you want to add member functions , You must override the template for
  that file.

- The parser and validator libraries are separate

## Features ##

Feature highlights of K OpenAPI Parser include:

* **High Performance** - The parser uses Kotlinx Serialization and doesn't convert the streams
  to strings and instead deserializes from input streams

* **Read/Write API** - The parser provides a read write api from which you can modify the open api spec 
  and then export it back to json

* **Tolerant Reader** - The parser yields a fully accessible result
  from any valid JSON or YAML input - whether the input is a
  valid OpenAPI specification or not. Default / null values are returned for

* **Separate validation** - All validation beyond basic
  JSON/YAML parsing can be performed after the initial parse. Validation goes beyond checking what can be
  expressed in JSON Schema, to include all requirements described in
  the OpenAPI specification.

* **Serialization** - Serialization to JSON or YAML is supported, and
  by default, round-tripping will not cause any reordering of model
  content.

* **Easy Evolution** - A YAML-based DSL is used to capture most of the
  details of the OpenAPI Specification. We use code generation to
  create interfaces and implementation classes. Manual code can be added to __KATE__ templates.

* **Flexible Reference Handling** - All references are detected
  during parsing, including references not technically permitted by
  the OpenAPI specification. References are normally traversed
  automatically by the API, but full details of references and
  their resolution status are also available.

* **Unpolluted API** - Many features of the parser API are not directly
  accessible from modeled objects, but are accessed via adapter objects.
  This ensures that these features will not collide with generated
  methods of the model API, even as new features are added to the
  OpenAPI specification in the future.

## Documentation

The [Getting Started Guide](GettingStarted.md) shows how to build the
software locally, and contains a simple sample program that shows how
to use the parser.

The [API Overview](API-Overview.md) describes the APIs presented in
the project, including the parser, the serializer, the read/write
model, and the treatment of references.

## License

K OpenAPI Parser is provided under the Eclipse Public License (https://www.eclipse.org/legal/epl-v10.html)

## Contributing

We welcome serious contributors. If you would like to work with us, open a new issue if you have
a suggestion or want to report a bug or omission.

## Resources

* [Getting Started Guide](GettingStarted.md)
* [API Overview](API-Overview.md)
