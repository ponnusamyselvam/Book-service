package com.webservice.book.dao;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.webservice.book.model.Author;
import com.webservice.book.model.Book;
import com.webservice.book.model.Graph;
import com.webservice.book.model.Publisher;
import com.webservice.book.model.Reader;

public class GraphService {

	public Graph getGraphData(AuthorService authorService, PublisherService publisherService, BookService bookService, ReaderService readerService) {
		Graph graph = new Graph();
		//List<Graph> list = new ArrayList<>();
		JsonObject graphData = new JsonObject();
		JsonArray nodes = new JsonArray();
		JsonArray edges = new JsonArray();
		List<String> nodeId = new ArrayList<>();
		JsonObject node = new JsonObject();
		JsonObject edge = new JsonObject();
		try {
			List<Author> authors = authorService.getAll();
			List<Publisher> publishers = publisherService.getAll();
			List<Book> books = bookService.getAll();
			List<Reader> readers = readerService.getAll();
			for (Author author : authors) {
				node = new JsonObject();
				String nodeid = author.getId() + "-" + author.getName();
				if(!nodeId.contains(nodeid)) {
					node.addProperty("id", nodeid);
					node.addProperty("label", author.getName());
					node.addProperty("color", "#42f5e6");
					nodeId.add(nodeid);
					nodes.add(node);
				}
			}
			for (Publisher publisher : publishers) {
				node = new JsonObject();
				String nodeid = publisher.getId() + "-" + publisher.getPublisher_name();
				if(!nodeId.contains(nodeid)) {
					node.addProperty("id", nodeid);
					node.addProperty("label", publisher.getPublisher_name());
					node.addProperty("color", "#90f542");
					nodeId.add(nodeid);
					nodes.add(node);
				}
			}
			for (Book book : books) {
				node = new JsonObject();
				String nodeid = book.getId() + "-" + book.getBook_name();
				String relativeNodeid;
				if(!nodeId.contains(nodeid)) {
					node.addProperty("id", nodeid);
					node.addProperty("label", book.getBook_name());
					node.addProperty("color", "#f5ec42");
					nodeId.add(nodeid);
					nodes.add(node);
				}
				Author author = book.getAuthor();
				if(author != null) {
					relativeNodeid = author.getId() +"-"+author.getName();
					if(nodeId.contains(relativeNodeid)) {
						edge = new JsonObject();
						edge.addProperty("from", nodeid);
						edge.addProperty("to", relativeNodeid);
						edges.add(edge);
					}
				}
				author = book.getCo_author_id();
				if(author != null) {
					relativeNodeid = author.getId() +"-"+author.getName();
					if(nodeId.contains(relativeNodeid)) {
						edge = new JsonObject();
						edge.addProperty("from", nodeid);
						edge.addProperty("to", relativeNodeid);
						edges.add(edge);
					}
				}
				Publisher publisher = book.getPublisher();
				if(publisher != null) {
					relativeNodeid = publisher.getId() +"-"+publisher.getPublisher_name();
					if(nodeId.contains(relativeNodeid)) {
						edge = new JsonObject();
						edge.addProperty("from", nodeid);
						edge.addProperty("to", relativeNodeid);
						edges.add(edge);
					}
				}
				
			}
			for (Reader reader : readers) {
				node = new JsonObject();
				String nodeid = reader.getId() + "-" + reader.getReader_name();
				String relativeNodeid;
				if(!nodeId.contains(nodeid)) {
					node.addProperty("id", nodeid);
					node.addProperty("label", reader.getReader_name());
					node.addProperty("color", "#e0a8a8");
					nodeId.add(nodeid);
					nodes.add(node);
				}
				Book book = reader.getBook();
				if(book != null) {
					relativeNodeid = book.getId() +"-"+book.getBook_name();
					if(nodeId.contains(relativeNodeid)) {
						edge = new JsonObject();
						edge.addProperty("from", nodeid);
						edge.addProperty("to", relativeNodeid);
						edges.add(edge);
					}
				}
				/*Publisher publisher = book.getPublisher();
				if(publisher != null) {
					relativeNodeid = publisher.getId() +"-"+publisher.getPublisher_name();
					if(nodeId.contains(relativeNodeid)) {
						edge = new JsonObject();
						edge.addProperty("from", nodeid);
						edge.addProperty("to", relativeNodeid);
						edges.add(edge);
					}
				}
				*/
			}
			graphData.add("nodes", nodes);
			graphData.add("edges", edges);
			graph.setGraph(graphData.toString());
//list.add(graph);
		} catch(Exception e) {
			
		}
		return graph;
	}

}
