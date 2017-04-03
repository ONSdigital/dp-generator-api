package dp.generatorapi.generators;

public interface GeneratorType {

    void addPage(final String content, final Filter filter);

    String convertPage(final Filter filter);
}
