package io.superbiz.baby.rest; /**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import io.superbiz.baby.model.Music;
import io.superbiz.baby.model.Music;
import io.superbiz.baby.services.MusicsService;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.logging.Logger;

@Path("musics")
@Produces({"application/json"})
@ApplicationScoped
public class MusicsResource {

    private static final Logger LOGGER = Logger.getLogger(MusicsResource.class.getName());

    @EJB
    private MusicsService service;


    @GET
    @Path("{id}")
    public Music find(@PathParam("id") Long id) {
        LOGGER.info("find: " + id);
        return service.find(id);
    }

    @GET
    public List<Music> getMusics() {
        return service.getMusics();
    }

    @POST
    @Consumes("application/json")
    public Music addMusic(Music movie) {
        service.addMusic(movie);
        return movie;
    }

    @DELETE
    @Path("{id}")
    public void deleteMusic(@PathParam("id") long id) {
        service.deleteMusic(id);
    }

    @PUT
    @Path("{id}")
    @Consumes("application/json")
    public Music updateMusic(@PathParam("id") long id, Music movie) {
        service.updateMusic(id, movie);
        return service.find(id);
    }


    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public int count(@QueryParam("field") String field, @QueryParam("searchTerm") String searchTerm) {
        return service.count();
    }

}