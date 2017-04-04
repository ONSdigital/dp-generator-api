package dp.generatorapi.controller;

import dp.generatorapi.Utils;
import dp.generatorapi.repository.ONSPage;
import dp.generatorapi.repository.PageRepository;
import dp.generatorapi.generators.GeneratorType;
import dp.generatorapi.generators.PageGeneratorFactory;
import dp.generatorapi.generators.Filter;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Generator {

    @Autowired
    private PageRepository pageRepository;

    @Autowired
    private PageGeneratorFactory factory;

    private final String language = "?lang=en";

    @RequestMapping("/generator")
    public ResponseEntity<String> generator(@RequestParam(value = "uri") final String uri,
                                            @RequestParam(value = "format") final String format,
                                            @RequestParam(value = "fromYear", required = false) final String fromYear,
                                            @RequestParam(value = "toYear", required = false) final String toYear,
                                            @RequestParam(value = "fromQuarter", required = false) final String fromQuarter,
                                            @RequestParam(value = "toQuarter", required = false) final String toQuarter,
                                            @RequestParam(value = "fromMonth", required = false) final String fromMonth,
                                            @RequestParam(value = "toMonth", required = false) final String toMonth,
                                            @RequestParam(value = "frequency", required = false) final String frequency
    ) {
        final Filter filter = new Filter(format, fromYear, toYear, fromQuarter, toQuarter, fromMonth, toMonth, frequency);
        final ONSPage page = pageRepository.findByUri(uri + language);
        if (page == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        final GeneratorType generator = factory.get(page.getContent());
        generator.addPage(page.getContent(), filter);
        final MultiValueMap headers = Utils.getHeaders(format);
        return new ResponseEntity<>(generator.convertPage(filter), headers, HttpStatus.OK);
    }
}
