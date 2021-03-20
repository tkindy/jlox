package com.tylerkindy.lox;

public class AstPrinter implements Expr.Visitor<String> {
  public String print(Expr expr) {
    return expr.accept(this);
  }

  @Override
  public String visitBinaryExpr(Expr.Binary expr) {
    return parenthesize(expr.operator().lexeme, expr.left(), expr.right());
  }

  @Override
  public String visitGroupingExpr(Expr.Grouping expr) {
    return parenthesize("group", expr.expression());
  }

  @Override
  public String visitLiteralExpr(Expr.Literal expr) {
    return expr.value().toString();
  }

  @Override
  public String visitUnaryExpr(Expr.Unary expr) {
    return parenthesize(expr.operator().lexeme, expr.right());
  }

  private String parenthesize(String name, Expr... exprs) {
    var builder = new StringBuilder()
      .append("(")
      .append(name);

    for (Expr expr : exprs) {
      builder
        .append(" ")
        .append(expr.accept(this));
    }

    return builder.append(")").toString();
  }
}
