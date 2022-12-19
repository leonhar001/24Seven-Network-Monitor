package application.model;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Target {

	private String name;
	private String link;
	private boolean isSelected;
	private boolean isOnline;

	@SuppressWarnings("unused")
	private InetAddress inet = null;

	public Target(String name, String link) {
		this.name = name;
		this.link = link;
		this.isSelected = false;
		this.isOnline = false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	public void checkStatus() throws IOException {
		try {
			this.inet = InetAddress.getByName(link);
			isOnline = true;
		} catch (UnknownHostException e) {
			isOnline = false;
		}
	}

	public boolean isOnline() {
		return isOnline;
	}

	@Override
	public String toString() {
		return "Target [name=" + name + ", link=" + link + "]";
	}

	public String toStringSaveFormat() {
		return name+"|"+link;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((link == null) ? 0 : getLink().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Target other = (Target) obj;
		if (link == null) {
			if (other.getLink() != null)
				return false;
		} else if (!link.equals(other.getLink()))
			return false;
		return true;
	}

}
