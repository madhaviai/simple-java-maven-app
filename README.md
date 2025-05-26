# GitHub Actions CI/CD Pipeline Setup with JFrog and Docker Compose

This repository contains the GitHub Actions pipeline configuration for deploying the Java Maven application using JFrog Artifactory and Docker Compose. The pipeline is configured to handle dependencies via JFrog repositories and deploy the application efficiently.

## Prerequisites

Before running the GitHub Actions pipeline, ensure the following are set up:

- **Docker**: Docker should be installed on your local machine for local testing or on the server where GitHub Actions runners will be used.
- **JFrog Artifactory**: You must have a JFrog Artifactory account and repositories set up to store the Maven artifacts and build reports. The pipeline requires access to the following repositories:
  - `opsera_customer1_m2` (for Maven dependencies)
  - `opsera_customer1_release` (for released JARs)
  - `opsera_customer1_test_report` (for storing test reports)

## Secret Variables for JFrog and Pipeline Configuration

Below is a list of secret names and their corresponding usernames that are expected by the GitHub Actions pipeline. These secrets are used in the `settings.xml` and the pipeline for authentication to JFrog repositories. Follow the procedure to create these secrets in your GitHub repository.

### Secrets and Their Usage

| Secret Name              | Description                                           | Expected by Code/Settings         |
|--------------------------|-------------------------------------------------------|----------------------------------|
| `JFROG_USERNAME`          | JFrog username for authentication                    | `settings.xml` and Pipeline      |
| `JFROG_M2_PASSWORD`          | JFrog password for `JFROG_USERNAME`               | `settings.xml`                   |
| `JFROG_TEST_REPORT_PASSWORD`   | JFrog password for test report repository       | Pipeline `Upload Test Reports`   |
| `JFROG_RELEASE_PASSWORD`  | JFrog password for the release repository            | Pipeline `Deploy to JFrog`       |

### Environment Variables and Their Usage

| Variable Name             | Description                                               | Used In                            | 
|---------------------------|-----------------------------------------------------------|------------------------------------|
| `JFROG_M2_REPO`           | JFrog Maven repo used to cache dependencies               | `settings.xml`, `pom.xml`          |                           |
| `JFROG_URL`               | Base URL of the JFrog Artifactory instance                | `mvn deploy`, `curl upload`        | 
| `JFROG_RELEASE_REPO`      | Repo used to upload build artifacts (JARs)                | `mvn deploy`, `curl upload`        | 
| `JFROG_TEST_REPORT_REPO`  | Repo used to store test result XMLs                       | `curl upload test reports`         | 
| `POM_VERSION`             | Maven project version used in deployment path naming      | `pom.xml`                          | 

### Procedure to Create Secrets in GitHub

1. **Login to GitHub**:
   - Open your GitHub repository.

2. **Add Secrets**:
   - Go to your GitHub repository.
   - Navigate to **Settings** → **Secrets** → **New repository secret**.
     - Add the following secrets:
       - `JFROG_USERNAME` → **username**
       - `JFROG_M2_PASSWORD` → **secret_value_for_maven**
       - `JFROG_TEST_REPORT_PASSWORD` → **secret_value_for_report**
       - `JFROG_RELEASE_PASSWORD` → **secret_value_for_release**

3. **Create GitHub Actions Workflow**:
   - Once the (`.github/workflows/buildAndReport.yml`) is updated with variables and secrets, the Pipeline can be created, and test reports can be verified successfully.
For complete details on setting up the GitHub pipeline, uploading environment variables, configuring secrets, and verifying test results, refer to the full documentation at the following link:

[Complete Setup and Verification Documentation](https://docs.google.com/document/d/1j6EXVC9it2fB4u2O_M7_rbGhmmA3nVH_6WUXlXMKe60/edit?usp=sharing)

Please follow the documentation for step-by-step guidance. Thank you!

