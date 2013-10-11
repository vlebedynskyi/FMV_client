package com.music.fmv.models.dbmodels;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * User: Vitalii Lebedynskyi
 * Date: 10/11/13
 * Time: 11:00 AM
 */
@DatabaseTable(tableName = "searh_query_cache")
public class SearchQueryCache extends BaseDBModel{
    public static enum QUERY_TYPE{
        SONG, ARTIST, ALBUM
    }

    @DatabaseField()
    private String query;

    @DatabaseField(dataType = DataType.ENUM_INTEGER)
    private QUERY_TYPE queryType;

    public SearchQueryCache(){}

    public SearchQueryCache(String query, QUERY_TYPE queryType) {
        this.query = query;
        this.queryType = queryType;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public QUERY_TYPE getQueryType() {
        return queryType;
    }

    public void setQueryType(QUERY_TYPE queryType) {
        this.queryType = queryType;
    }


    @Override
    public String toString() {
        return "id = " + getId() + ", Query = " +  query;
    }
}
