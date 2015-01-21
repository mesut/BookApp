package com.n11.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * From <a href="https://github.com/ktoso/maven-git-commit-id-plugin/tree/master">maven-git-commit-id-plugin</a>.
 */
@Component
public class GitRepositoryState {

	@Value("${git.branch}")
	public String branch;

	@Value("${git.commit.id.describe}")
	public String describe;

	@Value("${git.commit.id}")
	public String commitId;

	@Value("${git.commit.id.abbrev}")
	public String commitIdAbbrev;

	@Value("${git.build.user.name}")
	public String buildUserName;

	@Value("${git.build.user.email}")
	public String buildUserEmail;

	@Value("${git.build.time}")
	public String buildTime;

	@Value("${git.commit.user.name}")
	public String commitUserName;

	@Value("${git.commit.user.email}")
	public String commitUserEmail;

	@Value("${git.commit.message.full}")
	public String commitMessageFull;

	@Value("${git.commit.message.short}")
	public String commitMessageShort;

	@Value("${git.commit.time}")
	public String commitTime;

}
