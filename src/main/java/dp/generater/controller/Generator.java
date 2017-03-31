package dp.generater.controller;

import dp.generater.ONSPage;
import dp.generater.PageRepository;
import dp.generater.generators.GeneratorType;
import dp.generater.generators.PageGeneratorFactory;
import dp.generater.pages.Filter;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
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

    @RequestMapping("/generator")
    public ResponseEntity<String> generator(@RequestParam(value="uri") final String uri,
                                            @RequestParam(value="format") final String format,
                                            @RequestParam(value="fromYear", required = false) final String fromYear,
                                            @RequestParam(value="toYear", required = false) final String toYear,
                                            @RequestParam(value="fromQuarter" ,required = false) final String fromQuarter,
                                            @RequestParam(value="toQuarter", required = false) final String toQuarter,
                                            @RequestParam(value="fromMonth", required = false) final String fromMonth,
                                            @RequestParam(value="toMonth", required = false) final String toMonth,
                                            @RequestParam(value="frequency", required = false) final String frequency
                            ) {
        final Filter filter = new Filter(format,fromYear, toYear, fromQuarter, toQuarter, fromMonth, toMonth, frequency);
        final ONSPage page = pageRepository.findByUri(uri);
        final GeneratorType generator = factory.get(page.getContent());
        final String data =generator.convertPage(page.getContent(), filter);
        final MultiValueMap headers = new LinkedMultiValueMap();
        headers.add("Content-Disposition", "attachment; filename=data.csv");
        headers.add("Content-Type", "text/csv");
        return new ResponseEntity<String>(data, headers, HttpStatus.OK);
    }
}
