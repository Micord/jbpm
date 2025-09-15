package org.jbpm.persistence.api;

public final class CorrelationSettings {
  public static final int CORRELATION_KEY_LENGTH;

  static {

    String correlationKeyLengthStr = System.getenv("ORG_JBPM_CORRELATIONKEY_LENGTH");

    if (correlationKeyLengthStr == null) {
      correlationKeyLengthStr = System.getProperty("org.jbpm.correlationkey.length", "1000");
    }
    CORRELATION_KEY_LENGTH = Integer.parseInt(correlationKeyLengthStr);
  }

  private CorrelationSettings() {
  }
}
