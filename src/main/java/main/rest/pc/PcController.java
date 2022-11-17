package main.rest.pc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PcController {

    @Autowired
    PcRepository pcrr;

    @GetMapping("/pcs")
    public List<Pc> getPcs(){
        return pcrr.findAll();
    }

    @GetMapping("/pcs/{pcId}")
    public Pc getPc(@PathVariable String pcId){
        return pcrr.findById(Integer.parseInt(pcId)).orElseThrow();
    }

    @PostMapping("/pcs")
    public void addUser(@RequestBody Pc pc){
        pcrr.save(pc);
    }

    @PutMapping("/pcs/{pcId}")
    public void updateUser(@PathVariable String pcId, @RequestBody Pc pc){
        if(pcrr.existsById(Integer.parseInt(pcId)))
            pcrr.save(pc);
    }

    @DeleteMapping("/pcs/{pcId}")
    public void deletePc(@PathVariable String pcId){
        pcrr.deleteById(Integer.parseInt(pcId));
    }



}
