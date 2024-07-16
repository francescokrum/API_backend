package org.example.cati.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.File;

public class UploadImagem {

    public static boolean fazerUploadImagem(MultipartFile imagem){

        boolean sucessoUpload = false;

        if(imagem.isEmpty()){
            String nomeArquivo = imagem.getOriginalFilename();
            try{
                String pastaUploadImagem = "C:\\Users\\user\\Desktop\\PROJETO INTEGRADOR\\cati\\src\\main\\resources\\static\\images\\img-uploads";
                File dir = new File(pastaUploadImagem);

                if(!dir.exists()){
                    dir.mkdirs();
                }

                File serverFile = new File(dir.getAbsoluteFile() + File.separator + nomeArquivo);

                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));

                stream.write(imagem.getBytes());
                stream.close();

            } catch (Exception e) {

            }
        }

        return sucessoUpload;
    }
}
