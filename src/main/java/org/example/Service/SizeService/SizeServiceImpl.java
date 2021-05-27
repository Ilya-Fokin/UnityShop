package org.example.Service.SizeService;

import org.example.Domains.Size;
import org.example.Repository.SizeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SizeServiceImpl implements SizeService{
    @Autowired
    private SizeRepo sizeRepo;

    @Override
    public Long getSizeId(String name) {
        Size size = sizeRepo.findByName(name);
        if (size != null) {
            Long id = size.getId();
            if (id != null) {
                return id;
            } else
                return null;
        }else
            return null;

    }
}
