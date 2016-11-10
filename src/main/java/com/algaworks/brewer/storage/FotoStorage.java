package com.algaworks.brewer.storage;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by evandro on 24/09/16.
 */
public interface FotoStorage {

    public String salvarTemporariamente(MultipartFile[] files);
}
