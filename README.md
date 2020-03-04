# clj-kondo configurator

CLI tool to merge clj-kondo configurations.

## Why

Merging configurations may come in handy when you have one configuration with
settings you always want to apply on every project linted with clj-kondo and you
want to merge it with the existing one or just populate the initial one.

## Installation

Use as a git dep via `deps.edn`:

``` clojure
{:deps
 {clj-kondo-configurator
  {:git/url "https://github.com/borkdude/clj-kondo-configurator"
   :sha "4057a9e5c2070b6fbd69b2a7aa838c479f1e4ce1"}}}
```

Update SHA at your own convenience.

## Status

The API of this tool is not yet stable. Check [CHANGES.md](CHANGES.md) before
upgrading. More options may be added like validation of clj-kondo configs.

## Usage

Options to this tool are provided via the `--opts` argument which takes on EDN
map. Currenly `:merge` is the only option available which can be used to
deep-merge clj-kondo configuration files.

Example invocation:

``` clojure
$ clj -m clj-kondo-configurator.main --opts '{:merge ["examples/config1.edn" "examples/config2.edn"]}'
{:linters
 {:unsorted-required-namespaces {:level :warning},
  :unresolved-symbol {:exclude [(secretary.core/defroute)]},
  :missing-docstring {:level :off}},
 :lint-as
 #:me.raynes.conch{let-programs clojure.core/let,
                   programs clojure.core/declare},
 :unresolved-symbol {:exclude [(secretary.core/defroute)]}}
```

### Update existing config with template

``` clojure
$ clj -m clj-kondo-configurator.main \
 --opts '{:merge ["templates/.clj-kondo/config.edn" ".clj-kondo/config.edn"]}' \
 > .clj-kondo/config.edn
```

## License

Copyright © 2019 Michiel Borkent

Distributed under the EPL License, same as Clojure. See LICENSE.
