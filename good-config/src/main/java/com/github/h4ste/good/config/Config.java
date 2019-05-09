package com.github.h4ste.good.config;


import com.typesafe.config.ConfigException;
import com.typesafe.config.ConfigObject;
import com.typesafe.config.ConfigValue;
import com.typesafe.config.ConfigValueType;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Holds configuration options, see:
 * https://github.com/typesafehub/config
 */
@SuppressWarnings({"unused", "SameParameterValue"})
public final class Config {
  private final com.typesafe.config.Config conf;

  private Config(com.typesafe.config.Config conf) {
    this.conf = conf;
  }

  private static class LazyGlobalHolder {
    private static final Config INSTANCE  = new Config(com.typesafe.config.ConfigFactory.load());
  }

  public static Config global() {
    return LazyGlobalHolder.INSTANCE;
  }

  public static Config load(String path) {
    return new Config(LazyGlobalHolder.INSTANCE.conf.getConfig(path));
  }

  /**
   * Returns true if the {@code Config} contains no key-value
   * pairs.
   *
   * @return true if the configuration is empty
   */
  public boolean isEmpty() {
    return conf.isEmpty();
  }

  /**
   *
   * @param path
   *            path expression
   * @return the boolean value at the requested path
   * @throws ConfigException.Missing
   *             if value is absent or null
   * @throws ConfigException.WrongType
   *             if value is not convertible to boolean
   */
  public boolean getBoolean(String path) {
    return conf.getBoolean(path);
  }

  /**
   * @param path
   *            path expression
   * @return the numeric value at the requested path
   * @throws ConfigException.Missing
   *             if value is absent or null
   * @throws ConfigException.WrongType
   *             if value is not convertible to a number
   */
  public Number getNumber(String path) {
    return conf.getNumber(path);
  }

  /**
   * Gets the integer at the given path. If the value at the
   * path has a fractional (floating point) component, it
   * will be discarded and only the integer part will be
   * returned (it works like a "narrowing primitive conversion"
   * in the Java language specification).
   *
   * @param path
   *            path expression
   * @return the 32-bit integer value at the requested path
   * @throws ConfigException.Missing
   *             if value is absent or null
   * @throws ConfigException.WrongType
   *             if value is not convertible to an int (for example it is out
   *             of range, or it's a boolean value)
   */
  public int getInt(String path) {
    return conf.getInt(path);
  }

  /**
   * Gets the long integer at the given path.  If the value at
   * the path has a fractional (floating point) component, it
   * will be discarded and only the integer part will be
   * returned (it works like a "narrowing primitive conversion"
   * in the Java language specification).
   *
   * @param path
   *            path expression
   * @return the 64-bit long value at the requested path
   * @throws ConfigException.Missing
   *             if value is absent or null
   * @throws ConfigException.WrongType
   *             if value is not convertible to a long
   */
  public long getLong(String path) {
    return conf.getLong(path);
  }

  /**
   * @param path
   *            path expression
   * @return the floating-point value at the requested path
   * @throws ConfigException.Missing
   *             if value is absent or null
   * @throws ConfigException.WrongType
   *             if value is not convertible to a double
   */
  public double getDouble(String path) {
    return conf.getDouble(path);
  }

  /**
   * @param path
   *            path expression
   * @return the floating-point value at the requested path
   * @throws ConfigException.Missing
   *             if value is absent or null
   * @throws ConfigException.WrongType
   *             if value is not convertible to a double
   */
  public float getFloat(String path) {
    return Float.valueOf(conf.getString(path));
  }

  /**
   * @param path
   *            path expression
   * @return the string value at the requested path
   * @throws ConfigException.Missing
   *             if value is absent or null
   * @throws ConfigException.WrongType
   *             if value is not convertible to a string
   */
  public String getString(String path) {
    return conf.getString(path);
  }

  public File getFile(String path) {
    return new File(getString(path));
  }

  public File getFileSafe(String path) {
    final File file = new File(getString(path));
    //noinspection ResultOfMethodCallIgnored
    file.getParentFile().mkdirs();
    return file;
  }


  public Path getPath(String path) {
    return Paths.get(getString(path));
  }

  public <T> Class<? extends T> getClass(String classPath, Class<T> clazz) {
    try {
      return Class.forName(conf.getString(classPath)).asSubclass(clazz);
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  public Config getConfig(String path) {
    return new Config(conf.getConfig(path));
  }

  public Long getByteSize(String path) {
    return conf.getBytes(path);
  }

  public long getDuration(String path, TimeUnit unit) {
    return conf.getDuration(path, unit);
  }

  public List<Boolean> getBooleanList(String path) {
    return conf.getBooleanList(path);
  }

  public List<Number> getNumberList(String path) {
    return conf.getNumberList(path);
  }

  public List<Integer> getIntList(String path) {
    return conf.getIntList(path);
  }

  public List<Long> getLongList(String path) {
    return conf.getLongList(path);
  }

  public List<Double> getDoubleList(String path) {
    return conf.getDoubleList(path);
  }

  public List<String> getStringList(String path) {
    return conf.getStringList(path);
  }

  public <T> List<T> getTypedList(String path, Class<T> type) {
    return conf.getAnyRefList(path).stream().map(type::cast).collect(Collectors.toUnmodifiableList());
  }

  public <T> Set<T> getTypedSet(String path, Class<T> type) {
    return conf.getAnyRefList(path).stream().map(type::cast).collect(Collectors.toUnmodifiableSet());
  }

  public List<Long> getBytesList(String path) {
    return conf.getBytesList(path);
  }

  public List<Long> getDurationList(String path, TimeUnit unit) {
    return conf.getDurationList(path, unit);
  }

  private <T> Map<String, T> getMap(String path, ConfigValueType type, Function<ConfigValue, T> getter) {
    final ConfigObject subConfObj = conf.getObject(path);
    return Collections.unmodifiableMap(subConfObj.entrySet().stream()
        .filter(e -> e.getValue().valueType() == type)
        .collect(Collectors.toMap(Map.Entry::getKey, e -> getter.apply(e.getValue()))));
  }

  public Map<String, Boolean> getBooleanMap(String path) {
    return getMap(path, ConfigValueType.BOOLEAN, e -> (Boolean) e.unwrapped());
  }

  private <T extends Number> Map<String, T> getNumberMap(String path, Function<Number, T> getter) {
    return getMap(path, ConfigValueType.NUMBER, e -> getter.apply((Number) e.unwrapped()));
  }

  public Map<String, Long> getLongMap(String path) {
    return getNumberMap(path, Number::longValue);
  }

  public Map<String, Double> getDoubleMap(String path) {
    return getNumberMap(path, Number::doubleValue);
  }

  public Map<String, String> getStringMap(String path) {
    return getMap(path, ConfigValueType.STRING, e -> (String) e.unwrapped());
  }

  public void validateAgainst(Config reference, String... restrictToPaths) {
    conf.checkValid(reference.conf, restrictToPaths);
  }
}
