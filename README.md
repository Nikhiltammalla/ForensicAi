# ForensicAI

> A forensic investigation tool suite built with JavaFX and AWS services for facial recognition and sketch analysis.

---

## Table of Contents

- [Overview](#overview)
- [Project Structure](#project-structure)
- [Components](#components)
- [Requirements](#requirements)
- [Building](#building)
- [Configuration](#configuration)
- [License](#license)

---

## Overview

ForensicAI is a desktop-based forensic investigation platform that enables investigators to input facial sketches, upload them for cloud processing, and perform face matching using AWS Rekognition. It consists of two modules: a JavaFX desktop application and a Maven-based face matching service.

---

## Project Structure

```
ForensicAi_project/
├── ForensicAI v2/               # JavaFX desktop application
│   ├── src/                     # Java source files
│   ├── build.xml                # Ant build configuration
│   └── nbproject/               # NetBeans project configuration
└── ForensicAi_facematch/        # Face matching module (Maven)
    ├── src/                     # Java source files
    ├── pom.xml                  # Maven configuration
    └── target/                  # Build output (git-ignored)
```

---

## Components

### ForensicAI v2 — JavaFX Desktop App

A desktop application built with Apache Ant and JavaFX, designed for forensic investigators to:

- Input and manage sketch elements (facial features, markers)
- Upload sketches to AWS S3 for processing
- Interface with the face matching module

**Includes:**
- Splash screen, login, and dashboard UI
- FXML-based layouts with CSS styling
- Database connectivity module

### ForensicAi_FaceMatch — Maven Module

A Maven-based Java module for facial recognition that:

- Uses **AWS Rekognition** for face search and comparison
- Uses **AWS S3** for image storage
- Provides collection management APIs

---

## Requirements

| Requirement | Notes |
|---|---|
| Java 8+ | Required for both modules |
| JavaFX SDK | Required for the desktop app |
| Apache Maven | Required for the facematch module |
| AWS SDK for Java | Bundled via Maven dependencies |
| Git LFS | Optional — for large binary assets |

---

## Building

### ForensicAI v2

```bash
cd "ForensicAI v2"
ant clean
ant build
```

### ForensicAi_FaceMatch

```bash
cd ForensicAi_facematch
mvn clean install
```

---

## Configuration

AWS credentials are required for S3 and Rekognition access. Copy the credentials template and fill in your values:

```bash
cp "ForensicAI v2/src/forensicai/v2/elements/credentials.example" \
   "ForensicAI v2/src/forensicai/v2/elements/credentials"
```

Then edit the `credentials` file with your AWS access key, secret, and region.

> ⚠️ **Security Note:** The `credentials` file is excluded from version control via `.gitignore`. Never commit AWS credentials to your repository.

---

## License

See individual source files for license headers.