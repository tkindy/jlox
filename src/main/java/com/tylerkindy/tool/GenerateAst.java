package com.tylerkindy.tool;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class GenerateAst {

  public static void main(String[] args) throws IOException {
    if (args.length != 1) {
      System.err.println("Usage: generate_ast <output directory>");
      System.exit(64);
    }

    String outputDir = args[0];
    defineAst(
      outputDir,
      "Expr",
      List.of(
        "Binary   : Expr left, Token operator, Expr right",
        "Grouping : Expr expression",
        "Literal  : Object value",
        "Unary    : Token operator, Expr right"
      )
    );
  }

  private static void defineAst(String outputDir, String baseName, List<String> types)
    throws IOException {
    String path = outputDir + "/" + baseName + ".java";
    PrintWriter writer = new PrintWriter(path, StandardCharsets.UTF_8);

    writer.println("package com.tylerkindy.lox;");
    writer.println();
    writer.println("import java.util.List;");
    writer.println();
    writer.println("abstract class " + baseName + " {");

    // The AST classes.
    for (String type : types) {
      String[] parts = type.split(":");
      String className = parts[0].trim();
      String fields = parts[1].trim();
      defineType(writer, baseName, className, fields);
    }

    writer.println("}");
    writer.close();
  }

  private static void defineType(
    PrintWriter writer,
    String baseName,
    String className,
    String fieldList
  ) {
    writer.println("  static class " + className + " extends " + baseName + " {");

    // Fields
    String[] fields = fieldList.split(", ");
    for (String field : fields) {
      writer.println("    final " + field + ";");
    }

    // Constructor
    writer.println();
    writer.println("    " + className + "(" + fieldList + ") {");

    // Store parameters in fields
    for (String field : fields) {
      String name = field.split(" ")[1];
      writer.println("      this." + name + " = " + name + ";");
    }

    writer.println("    }");
    writer.println("  }");
  }
}
