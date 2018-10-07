package com.example.demo.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Component
@Slf4j
public class TestService {

    public void doDeployChain(String json) {

        try {
            File tmpFile = File.createTempFile("TMP", "JSON");
            BufferedWriter bw = new BufferedWriter(new FileWriter(tmpFile));
            bw.write(json);
            bw.close();
            String[] cmds={"/bin/sh","-c","/home/fuhrpark-spring/webhook/gitHook.sh < "+tmpFile.getAbsolutePath()};
            Process p=Runtime.getRuntime().exec(cmds);
            p.waitFor();
            log.info(tmpFile.getAbsolutePath());
        } catch (IOException e) {
            log.error("error creating tmp file");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
