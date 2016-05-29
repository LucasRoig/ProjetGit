package core;

/**
 * Représente les différentes propriétés d'un objet git, telles que son nom, l'auteur de commit, la date d'un commit...
 */
public class GitProperty<T> {
	public enum PropertyType{
		Name,TagChild,Type,AuthorName,AuthorMail,Date,TreeId,AuthorDate,CommitterName,CommitterMail,CommitterDate,ParentList,TreeEntries;
	}
	
	private PropertyType type;
	private T value;
	
	public GitProperty(PropertyType type, T value) {
		this.type = type;
		this.value = value;
	}
	
	public PropertyType getType() {
		return type;
	}
	
	public T getValue() {
		return value;
	}
}
