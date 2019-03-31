package com.nitesh.MvsProject.dao.scylla;


import com.nitesh.MvsProject.dao.resource.ScyllaResource;
import com.nitesh.MvsProject.dao.scylla.accessor.JukeBoxSearchIndexAccessor;
import com.nitesh.MvsProject.models.entity.SearchIndex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class SearchIndexDao {
    @Autowired
    ScyllaResource scyllaResource;

    public SearchIndex getIndexByTypeAndName(String type, String name) throws DataAccessException {
        JukeBoxSearchIndexAccessor accessor =  this.scyllaResource.getAccessor(JukeBoxSearchIndexAccessor.class);
        return accessor.getIdByNameforTypeOfSearch(type, name);
    }

    public void addIndex(String type, String name, String id) throws DataAccessException {
        JukeBoxSearchIndexAccessor accessor = this.scyllaResource.getAccessor(JukeBoxSearchIndexAccessor.class);
        ArrayList<String> ids = new ArrayList<>();
        ids.add(id);
        accessor.addIdToIndex(ids, type, name);
    }
}
