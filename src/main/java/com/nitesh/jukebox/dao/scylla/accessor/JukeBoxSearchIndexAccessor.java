//package com.nitesh.jukebox.dao.scylla.accessor;
//
//
//import com.datastax.driver.mapping.annotations.Accessor;
//import com.datastax.driver.mapping.annotations.Query;
//import com.nitesh.jukebox.models.entity.SearchIndex;
//
//import java.util.List;
//
//
//@Accessor
//public interface JukeBoxSearchIndexAccessor {
//    @Query("SELECT * FROM jukebox.search_index WHERE search_type = :type AND value = :value")
//    SearchIndex getIdByNameforTypeOfSearch(String type, String value);
//
//    @Query("UPDATE jukebox.search_index SET ids = ids + :id WHERE search_type = :type AND value = :value")
//    void addIdToIndex(List<String> id, String type, String value);
//}
