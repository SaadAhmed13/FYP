import java.util.ArrayList;

public class SimilarityStructure {

	ArrayList <String> EnglishSentences;
	ArrayList <String> UrduSentences;
	ArrayList <String> SwappedSetences;
	double maxSimilarityValue;
	String maxSimilaritySentence;
	
	SimilarityStructure()
	{
		this.EnglishSentences = new ArrayList<String>();
		this.UrduSentences = new ArrayList<String>();
		this.SwappedSetences = new ArrayList<String>();
		this.maxSimilaritySentence = new String();
	}
}
