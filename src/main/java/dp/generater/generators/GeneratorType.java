package dp.generater.generators;

import dp.generater.pages.Filter;

public interface GeneratorType {

    String convertPage(final String content, final Filter filter);
}
