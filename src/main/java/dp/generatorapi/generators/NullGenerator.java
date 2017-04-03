package dp.generatorapi.generators;

import dp.generatorapi.exceptions.UnsupportedFormatException;

public class NullGenerator implements GeneratorType {

    @Override
    public void addPage(String content, Filter filter) {
        throw new UnsupportedFormatException();
    }

    @Override
    public String convertPage(Filter filter) {
        throw new UnsupportedFormatException();
    }
}
