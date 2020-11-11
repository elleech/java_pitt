package Classes;

/**
 * This is for INFSCI-2140 in 2020
 * YEC24@pitt.edu
 */

public class Query {
	// you can modify this class
	private String queryContent;	
	private String topicId;

	/*
	 * [STRUCTURES & THOUGHTS]
	 * <essential data>
	 * "queryContent" --> everything from <title> to before <\top>
	 * "topicId"      --> the digits in <num>
	 * "title"        --> content from <title>
	 * "desc"         --> content from <desc>
	 * "narr"         --> content from <narr>
	 * 
	 * <class methods>
	 * Constructor     --> initialize data
	 * Getters         --> return corresponding data
	 * SetQueryContent --> 1) set queryContent (String)
	 *                     2) extract and set title (String), desc (String), and narr (String)
	 * SetTopicId      --> set topicId (String)
	 * 
	 * <note>
	 * all data should be: 1) toLowerCase, 2) tokenize, 3) remove stopWords, 4) stem, by ExtractQuery.java
	 */
	private String title;
	private String desc;
	private String narr;

	// initialize Query object
	public Query() {
		this.queryContent = "";
		this.topicId = "";
		this.title = "";
		this.desc = "";
		this.narr = "";
	}

	public String GetQueryContent() {
		return this.queryContent;
	}
	public String GetTopicId() {
		return this.topicId;
	}
	public void SetQueryContent(String content) {
		this.queryContent = content;

		// extract title, desc, and narr
		int idxTitle = this.queryContent.indexOf("<title>");
		int idxDesc = this.queryContent.indexOf("<desc>");
		int idxNarr = this.queryContent.indexOf("<narr>");

		this.title = this.queryContent.substring(idxTitle, idxDesc).replaceAll("<title> ", "");
		this.desc = this.queryContent.substring(idxDesc, idxNarr).replaceAll("<desc> descript ", "");
		this.narr = this.queryContent.substring(idxNarr).replaceAll("<narr> narr ", "");
	}	
	public void SetTopicId(String id) {
		this.topicId = id;
	}

	public String GetTitle() {
		return this.title;
	}
	public String GetDesc() {
		return this.desc;
	}
	public String GetNarr() {
		return this.narr;
	}
}
