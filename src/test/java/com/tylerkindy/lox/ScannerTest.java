package com.tylerkindy.lox;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class ScannerTest {

  @Test
  void strings() {
    assertThat(new Scanner("\"hello\"").scanTokens())
      .isEqualTo(
        List.of(
          new Token(TokenType.STRING, "\"hello\"", "hello", 1),
          new Token(TokenType.EOF, "", null, 1)
        )
      );
  }

  @Test
  void identifiers() {
    assertThat(new Scanner("foo").scanTokens())
      .isEqualTo(
        List.of(
          new Token(TokenType.IDENTIFIER, "foo", null, 1),
          new Token(TokenType.EOF, "", null, 1)
        )
      );
  }

  @Test
  void keywords() {
    assertThat(new Scanner("and").scanTokens())
      .isEqualTo(
        List.of(
          new Token(TokenType.AND, "and", null, 1),
          new Token(TokenType.EOF, "", null, 1)
        )
      );
  }

  @Test
  void blockComments() {
    assertThat(new Scanner("/*\ntesting\ntesting testing\n*/").scanTokens())
      .isEqualTo(List.of(new Token(TokenType.EOF, "", null, 4)));
  }
}
