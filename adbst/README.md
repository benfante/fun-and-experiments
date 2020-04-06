# Experiments on BST

Experiment on an implementation of a BST chatting with [AD](https://gist.github.com/andreadeleo98).

The starting point is from https://gist.github.com/andreadeleo98/cfa47ccd298109830d3ffaa13c7f32b0

## Test, Build and Run

You can run the tests of this project typing:

```bash
./mvnw test
```

Build it with:

```bash
./mvnw clean verify
```

Run it with:

```bash
./mvnw exec:java -Dexec.mainClass="com.benfante.fun.adbst.FST"
```

You can skip tests using the `-DskipTests` option, i.e:

```bash
./mvnw -DskipTests clean verify
```
