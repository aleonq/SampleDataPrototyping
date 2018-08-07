package com.support.prototyping.sample.views.recyclerview;

interface ErrorMessage {
    String ATTRIBUTE_NULL = "Sample data attributes must be provided in order to inflate the RecyclerView.";
    String LISTITEM_MISSING = "List item must be defined in attributes.";

    // Text
    String TEXTVIEW_ID_MISSING = "Data source has been provided but no view id has been provided to put this data into. Valid view id should be provided for matching data.";
    String TEXTVIEW_DATASOURCE_MISSING = "View id has been provided but no matching data source has been provided. charSequence_textview_data should be provided if view id is provided.";
    String TEXTVIEW_DATASOURCE_EMPTY = "Data source provided is empty. charSequence_textview_data should not be empty.";
    // Image
    String IMAGEVIEW_ID_MISSING = "Data source has been provided but no view id has been provided to put this data into. Valid view id should be provided for matching data";
    String IMAGEVIEW_DATASOURCE_MISSING = "View id has been provided but no matching data source has been provided. imageview1DataArrayResourceId should be provided if view id is provided";
    String IMAGEVIEW_DATASOURCE_EMPTY = "Data source provided is empty. SampleEnabledRecyclerView_listdata_image should not be empty";

    String MISSING_ATTRIBUTE = "Missing attribute:";
    String EMPTY_DATA = "Data empty:";
}
