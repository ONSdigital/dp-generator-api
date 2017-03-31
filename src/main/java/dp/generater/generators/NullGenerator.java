package dp.generater.generators;

import dp.generater.pages.Filter;

public class NullGenerator implements GeneratorType {

    @Override
    public String convertPage(final String content, final Filter filter){
        throw new RuntimeException("Invalid pages type");
    }
}
