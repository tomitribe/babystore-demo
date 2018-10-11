/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.superbiz.baby.services;

import io.superbiz.baby.model.Music;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Singleton
@Lock(LockType.READ)
public class MusicsService {

    public List<Music> catalog;

    public int id = 0;

    public List<Music> getCatalog() {
        return catalog;
    }

    public MusicsService() {
        catalog = new ArrayList();
        load();

    }


    public Music addMusic(Music music) {
        this.id++;
        music.setId(this.id);
        catalog.add(music);
        return music;
    }


    public List<Music> getMusics() {
        return catalog;
    }


    public void deleteMusic(long id) {
        boolean b = catalog.removeIf(obj -> obj.getId() == id);
    }

    public Music find(Long id) {
        for (Music music : catalog) {
            if (music.getId() == id) {
                return music;
            }
        }
        return null;
    }


    public void updateMusic(Long id, Music newMusicData) {
        Music oldMusicData = find(id);
        if (newMusicData.getName() != null) {
            oldMusicData.setName(newMusicData.getName());
        }

        if (newMusicData.getCategory() != null) {
            oldMusicData.setCategory(newMusicData.getCategory());
        }
    }


    public int count() {
        return catalog.size();
    }

    public void clear() {
        catalog.clear();
    }

    public void load() {
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("musics.txt");
        Scanner scanner = new Scanner(is);
        scanner.useDelimiter(",");
        while (scanner.hasNextLine()) {
            String[] line =  scanner.nextLine().split(",");
            addMusic(
                    new Music(
                            line[0],
                            line[1],
                            line[2]
                    )
            );
        }
    }

}