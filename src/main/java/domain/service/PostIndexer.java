package domain.service;

import com.google.gson.Gson;
import domain.dto.TotalPost;
import domain.entity.Post;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import util.EngineConstants;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class PostIndexer {
    private PostIndexer() {
    }

    public static void index() throws IOException {
        try (
                Directory directory = FSDirectory.open(new File(EngineConstants.Path.INDEX_PATH).toPath());
                IndexWriter indexWriter = new IndexWriter(directory, new IndexWriterConfig(new StandardAnalyzer()))
        ) {
            TotalPost allPost = new Gson().fromJson(new FileReader(EngineConstants.Path.POST_JSON_PATH), TotalPost.class);
            for (Post post : allPost.getPosts()) {
                indexWriter.updateDocument(
                        new Term(EngineConstants.IndexField.ID, post.getId()),
                        mapToDocs(post)
                );
            }
            indexWriter.commit();
        } catch (IOException e) {
            throw new IOException();
        }
    }

    private static Document mapToDocs(Post post) {
        Document document = new Document();
        document.add(new StringField(EngineConstants.IndexField.ID, post.getId(), Field.Store.YES));
        document.add(new StringField(EngineConstants.IndexField.TITLE, post.getTitle(), Field.Store.YES));
        document.add(new StringField(EngineConstants.IndexField.WRITER, post.getWriter(), Field.Store.YES));
        document.add(new StringField(EngineConstants.IndexField.CONTENT, post.getContent(), Field.Store.YES));
        return document;
    }
}
