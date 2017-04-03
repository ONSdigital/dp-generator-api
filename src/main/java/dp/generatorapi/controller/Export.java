package dp.generatorapi.controller;

import dp.generatorapi.Utils;
import dp.generatorapi.generators.GeneratorType;
import dp.generatorapi.generators.PageGeneratorFactory;
import dp.generatorapi.generators.Filter;
import dp.generatorapi.repository.ONSPage;
import dp.generatorapi.repository.PageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@RestController
public class Export {

    @Autowired
    private PageRepository pageRepository;

    @Autowired
    private PageGeneratorFactory factory;

    @RequestMapping(value = "/export", method = RequestMethod.POST)
    public ResponseEntity<String> generator(@ModelAttribute final ExportForm form) {
            final Filter filter = new Filter();
            GeneratorType generator = null;
            filter.setFormat(form.getFormat());
            for (String uri : form.getUri()) {
                final ONSPage page = pageRepository.findByUri(uri + "?lang=en");
                if (page == null) {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
                if (generator == null) {
                    generator = factory.get(page.getContent());
                }
                generator.addPage(page.getContent(), filter);
            }
            final MultiValueMap headers = Utils.getHeaders(form.getFormat());
            return new ResponseEntity<>(generator.convertPage(filter), headers, HttpStatus.OK);
    }

}
