package com.example.demo.services;

import com.example.demo.model.RepoEntity;
import com.example.demo.repositories.RepoEntityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Component
@Slf4j
public class TestService {

    private final RepoEntityRepository repoEntityRepository;

    public TestService(RepoEntityRepository repoEntityRepository) {
        this.repoEntityRepository = repoEntityRepository;
    }

    public void doDeployChain(String repoName, String json) {

        RepoEntity repoEntity =  repoEntityRepository.findByRepoName(repoName);

        String buildScript = repoEntity.getScript();

        File tmpFile;

        try {
            tmpFile = File.createTempFile("TMP", "JSON");
            BufferedWriter bw = new BufferedWriter(new FileWriter(tmpFile));
            bw.write(json);
            bw.close();
            String[] cmds={"/bin/sh","-c",buildScript +" < "+tmpFile.getAbsolutePath()};
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
