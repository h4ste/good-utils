package com.github.h4ste.util;

/**
 * This utility class provides methods for returning standard error codes. This is essentially a
 * Java-version of sysexits.h
 */
@SuppressWarnings("unused")
public final class Fatal {

  private Fatal() {
    throw new UnsupportedOperationException("Cannot create utility class");
  }

  /**
   * The command was used incorrectly, e.g., with the wrong number of arguments, a bad flag, a bad
   * syntax in a parameter, or whatever.
   */
  public static void usage() {
    ErrorCodes.USAGE.exit();
  }

  /**
   * The input data was incorrect in some way. This should only be used for user's data & not system
   * files.
   */
  public static void dataError() {
    ErrorCodes.DATAERR.exit();
  }

  /**
   * An input file (not a system file) did not exist or was not readable.  This could also include
   * errors like "No message" to a mailer (if it cared to catch it).
   */
  public static void noInput() {
    ErrorCodes.NOINPUT.exit();
  }

  /**
   * The user specified did not exist.  This might be used for mail addresses or remote logins.
   */
  public static void noUser() {
    ErrorCodes.NOUSER.exit();
  }

  /**
   * The host specified did not exist.  This is used in mail addresses or network requests
   */
  public static void noHost() {
    ErrorCodes.NOHOST.exit();
  }

  /**
   * A service is unavailable.  This can occur if a support program or file does not exist.  This
   * can also be used as a catchall message when something you wanted to do doesn't work, but you
   * don't know why.
   */
  public static void serviceUnavailable() {
    ErrorCodes.UNAVAILABLE.exit();
  }

  /**
   * An internal software error has been detected. This should be limited to non-operating system
   * related errors as possible.
   */
  public static void softwareError() {
    ErrorCodes.SOFTWARE.exit();
  }

  /**
   * An operating system error has been detected. This is intended to be used for such things as
   * "cannot fork", "cannot create pipe", or the like.  It includes things like getuid returning a
   * user that does not exist in the passwd file.
   */
  public static void operatingSystemError() {
    ErrorCodes.OSERR.exit();
  }

  /**
   * Some system file (e.g., /etc/passwd, /etc/utmp, etc.) does not exist, cannot be opened, or has
   * some sort of error (e.g., syntax error).
   */
  public static void operatingSystemfileError() {
    ErrorCodes.OSFILE.exit();
  }

  /**
   * A (user specified) output file cannot be created.
   */
  public static void fileCreationError() {
    ErrorCodes.CANTCREAT.exit();
  }

  /**
   * An error occurred while doing I/O on some file.
   */
  public static void ioError() {
    ErrorCodes.IOERR.exit();
  }

  /**
   * Temporary failure, indicating something that is not really an error.  In sendmail, this means
   * that a mailer (e.g.) could not create a connection, and the request should be reattempted
   * later.
   */
  public static void temporaryFailure() {
    ErrorCodes.TEMPFAIL.exit();
  }

  /**
   * The remote system returned something that was "not possible" during a protocol exchange.
   */
  public static void protocolError() {
    ErrorCodes.PROTOCOL.exit();
  }

  /**
   * You did not have sufficient permission to perform the operation.  This is not intended for file
   * system problems, which should use noInputError() or fileCreationError(),
   * but rather for higher level permissions.
   */
  public static void permissionError() {
    ErrorCodes.NOPERM.exit();
  }

  /**
   * An error occurred during software configuration.
   */
  public static void configError() {
    ErrorCodes.CONFIG.exit();
  }

  @SuppressWarnings("SpellCheckingInspection")
  private enum ErrorCodes {
    USAGE(64),
    DATAERR(65),
    NOINPUT(66),
    NOUSER(67),
    NOHOST(68),
    UNAVAILABLE(69),
    SOFTWARE(70),
    OSERR(71),
    OSFILE(72),
    CANTCREAT(73),
    IOERR(74),
    TEMPFAIL(75),
    PROTOCOL(76),
    NOPERM(77),
    CONFIG(78);

    private final int linuxErrorCode;

    ErrorCodes(int linuxErrorCode) {
      this.linuxErrorCode = linuxErrorCode;
    }

    private void exit() {
      System.exit(this.linuxErrorCode);
    }
  }
}
