/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import reference.Reference;

public class DaoService {
        private Set<String> bibtexids;
        DAO[] daos;
    
    public DaoService (DAO... newDaos) {
        
        bibtexids = new HashSet<>();
        daos = newDaos;
    }
    
    private void updateDaos() {
            for (DAO dao : daos) {           
            List<Reference> refs = dao.findAll();
            for (Reference ref : refs)
                bibtexids.add(ref.getField("bibTexId"));
        }
        
    }
    
    public String verifyBibTexId(String candidate) {
        
        updateDaos();        
        if (bibtexids.contains(candidate))
            return autoGenerate(candidate);
        return candidate;

    }
    
    private String autoGenerate(String candidate) {
        int i = 2;    
        while (bibtexids.contains(candidate + "_" + i)) {
                i++;
            }
        return candidate + "_" + i;
        
    }
}