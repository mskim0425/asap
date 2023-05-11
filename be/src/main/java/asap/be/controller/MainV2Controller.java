package asap.be.controller;

import asap.be.dto.EverythingPageDto;
import asap.be.search.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MainV2Controller {
    private final SearchService searchService;

    @GetMapping("/search")
    public ResponseEntity<List<EverythingPageDto>> search(@RequestParam(value = "lastId", required = false) Integer lastId,
                                                          @RequestParam("pName") String pName,
                                                          @RequestParam(value = "order", defaultValue = "DESC") String order){
        List<EverythingPageDto> searchList = searchService.search(lastId, pName, order);
        return new ResponseEntity<>(searchList, HttpStatus.OK);
    }


}
