package domain.service;

import domain.entity.Post;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static util.EngineConstants.IndexField.*;
import static util.EngineConstants.Path.INDEX_PATH;
import static util.EngineConstants.Type.WILDCARD;

public class PostSearcher {
    private String keyword;
    private final List<Post> result;

    public PostSearcher() {
        this.result = new ArrayList<>();
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public List<Post> getResult() throws IOException {
        IndexSearcher indexSearcher = new IndexSearcher(DirectoryReader.open(getDirectory()));

        BooleanQuery.Builder booleanQueryBuilder = new BooleanQuery.Builder();
        makeQuery(keyword).forEach(query -> booleanQueryBuilder.add(query, BooleanClause.Occur.SHOULD));
        TopDocs topDocs = indexSearcher.search(booleanQueryBuilder.build(), 20);

        for (int index = 0; index < topDocs.totalHits.value; index++) {
            Document document = indexSearcher.doc(topDocs.scoreDocs[index].doc);
            result.add(getIndexOption(document));
        }
        return result;
    }

    private Directory getDirectory() throws IOException {
        File indexDirectory = new File(INDEX_PATH);
        return FSDirectory.open(indexDirectory.toPath());
    }

    private List<WildcardQuery> makeQuery(String keyword) {
        return Arrays.asList(
                new WildcardQuery(new Term(TITLE, getWildcard(keyword))),
                new WildcardQuery(new Term(WRITER, getWildcard(keyword))),
                new WildcardQuery(new Term(CONTENT, getWildcard(keyword)))
        );
    }

    private String getWildcard(String keyword) {
        return WILDCARD + keyword + WILDCARD;
    }

    private Post getIndexOption(Document document) {
        return new Post(
                document.get(ID),
                document.get(TITLE),
                document.get(WRITER),
                document.get(CONTENT)
        );
    }
}
