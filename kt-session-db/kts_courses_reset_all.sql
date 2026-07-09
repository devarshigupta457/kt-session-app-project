-- ============================================================
--  KTS_COURSES — FULL RESET & RESEED (all 16 courses)
--  Deletes every existing row, then inserts all courses.
--  All courses are free; C_LINK points to /<filename>
-- ============================================================

TRUNCATE  KTS_COURSES;

INSERT INTO KTS_COURSES (C_ID, C_TITLE, C_DESC, C_PRICE, C_LINK, C_TRENDING) VALUES
('KTS001',
 'Apache Kafka - Interview Notes',
 'Interview-focused Apache Kafka notes covering core concepts (topics, partitions, offsets, consumer groups, delivery semantics, replication and ISR), Spring Boot integration with KafkaTemplate and @KafkaListener, retries and Dead Letter Topics, local Docker (KRaft) setup, quick-fire Q&A, and a config cheat sheet.',
 'Free', '/kafka-interview-notes.html', 'Yes');

INSERT INTO KTS_COURSES (C_ID, C_TITLE, C_DESC, C_PRICE, C_LINK, C_TRENDING) VALUES
('KTS002',
 'Core Java - Interview Notes',
 'Core Java interview notes spanning OOP fundamentals, JVM/JRE/JDK and bytecode, memory (stack, heap and garbage collection), collections and generics, exception handling, multithreading and concurrency, and modern Java 8+ features like streams, lambdas and Optional, with quick-fire Q&A and a cheat sheet.',
 'Free', '/core-java-interview-notes.html', 'Yes');

INSERT INTO KTS_COURSES (C_ID, C_TITLE, C_DESC, C_PRICE, C_LINK, C_TRENDING) VALUES
('KTS003',
 'HTML - Interview Notes',
 'HTML interview notes on elements, tags and attributes, block vs inline, semantic markup, forms and validation, metadata and SEO, media embeds, and accessibility best practices, plus quick-fire Q&A and a reference cheat sheet.',
 'Free', '/html-interview-notes.html', 'No');

INSERT INTO KTS_COURSES (C_ID, C_TITLE, C_DESC, C_PRICE, C_LINK, C_TRENDING) VALUES
('KTS004',
 'CSS - Interview Notes',
 'CSS interview notes covering selectors, specificity and the cascade, inheritance, the box model, box-sizing, Flexbox and Grid layout, positioning, units, responsive mobile-first design, pseudo-classes and custom properties, with quick-fire Q&A and a cheat sheet.',
 'Free', '/css-interview-notes.html', 'No');

INSERT INTO KTS_COURSES (C_ID, C_TITLE, C_DESC, C_PRICE, C_LINK, C_TRENDING) VALUES
('KTS005',
 'JavaScript - Interview Notes',
 'JavaScript interview notes on var/let/const and scope, closures, this-binding, prototypes and classes, promises and async/await, the event loop (microtasks vs macrotasks), array methods, and DOM events with event delegation, plus quick-fire Q&A and a cheat sheet.',
 'Free', '/javascript-interview-notes.html', 'Yes');

INSERT INTO KTS_COURSES (C_ID, C_TITLE, C_DESC, C_PRICE, C_LINK, C_TRENDING) VALUES
('KTS006',
 'Git - Interview Notes',
 'Git interview notes covering the three areas, everyday commands, .gitignore, branching, merge vs rebase, conflict resolution, remotes and the pull-request workflow, and undoing changes safely (reset vs revert, stash, reflog), with quick-fire Q&A and a cheat sheet.',
 'Free', '/git-interview-notes.html', 'No');

INSERT INTO KTS_COURSES (C_ID, C_TITLE, C_DESC, C_PRICE, C_LINK, C_TRENDING) VALUES
('KTS007',
 'Spring Boot - Complete Guide',
 'An in-depth Spring Boot guide covering auto-configuration internals and @Conditional, the IoC container and dependency injection, externalized config and profiles, REST controllers with global exception handling and validation, Spring Data JPA with transactions and the N+1 problem, HikariCP and Oracle tuning, AOP, Actuator and Micrometer, testing slices with Testcontainers, and Spring Security with stateless JWT, plus quick-fire Q&A and a cheat sheet.',
 'Free', '/springboot-guide.html', 'Yes');

INSERT INTO KTS_COURSES (C_ID, C_TITLE, C_DESC, C_PRICE, C_LINK, C_TRENDING) VALUES
('KTS008',
 'Spring Batch - Complete Guide',
 'An in-depth Spring Batch guide covering the Job, JobInstance, JobExecution and Step domain model, chunk-oriented processing and the chunk transaction, tasklet steps, built-in readers, processors and writers, modern Spring Boot 3 configuration, launching and JobParameters, restartability, skip and retry fault tolerance, listeners, and scaling with multi-threading and partitioning, plus quick-fire Q&A and a cheat sheet.',
 'Free', '/springbatch-guide.html', 'No');

INSERT INTO KTS_COURSES (C_ID, C_TITLE, C_DESC, C_PRICE, C_LINK, C_TRENDING) VALUES
('KTS009',
 'Angular - Complete Guide',
 'An in-depth Angular guide covering components, templates and lifecycle hooks, the four data bindings, directives and modern control flow, component communication with Input and Output, services and dependency injection, routing with guards and lazy loading, HttpClient and RxJS operators, template-driven and reactive forms, change detection with OnPush, Signals, and pipes, plus quick-fire Q&A and a cheat sheet.',
 'Free', '/angular-guide.html', 'Yes');

INSERT INTO KTS_COURSES (C_ID, C_TITLE, C_DESC, C_PRICE, C_LINK, C_TRENDING) VALUES
('KTS010',
 'Databases and SQL - Complete Guide',
 'An in-depth relational database and SQL guide covering the relational model and keys, normalization, joins and grouping, subqueries and CTEs, indexes and query plans, ACID and isolation levels, locking and deadlocks, and advanced SQL including views, stored procedures, triggers and window functions, plus quick-fire Q&A and a cheat sheet.',
 'Free', '/database-guide.html', 'Yes');

INSERT INTO KTS_COURSES (C_ID, C_TITLE, C_DESC, C_PRICE, C_LINK, C_TRENDING) VALUES
('KTS011',
 'Oracle Database - Complete Guide',
 'An in-depth Oracle Database guide covering instance vs database architecture, memory and background processes, tablespaces and schemas, data types and objects, Oracle-specific SQL such as ROWNUM, MERGE and hierarchical queries, PL/SQL with cursors, exceptions and bulk operations, indexing, and redo and undo transactions, plus quick-fire Q&A and a cheat sheet.',
 'Free', '/oracle-guide.html', 'No');

INSERT INTO KTS_COURSES (C_ID, C_TITLE, C_DESC, C_PRICE, C_LINK, C_TRENDING) VALUES
('KTS012',
 'MySQL - Complete Guide',
 'An in-depth MySQL guide covering storage engines and InnoDB, data types, SQL features and JSON, clustered indexes and query tuning with EXPLAIN, transactions and MVCC, isolation levels and locking, and replication and high availability, plus quick-fire Q&A and a cheat sheet.',
 'Free', '/mysql-guide.html', 'No');

INSERT INTO KTS_COURSES (C_ID, C_TITLE, C_DESC, C_PRICE, C_LINK, C_TRENDING) VALUES
('KTS013',
 'PostgreSQL - Complete Guide',
 'An in-depth PostgreSQL guide covering rich data types and JSONB, powerful SQL features like RETURNING, ON CONFLICT and recursive CTEs, the full index family (B-tree, GIN, GiST, BRIN and partial), MVCC and isolation, VACUUM and bloat, and extensions and partitioning, plus quick-fire Q&A and a cheat sheet.',
 'Free', '/postgresql-guide.html', 'Yes');

INSERT INTO KTS_COURSES (C_ID, C_TITLE, C_DESC, C_PRICE, C_LINK, C_TRENDING) VALUES
('KTS014',
 'Python - Complete Guide',
 'An in-depth Python guide covering mutability and identity, core data structures and comprehensions, functions with args and kwargs, decorators, OOP and dunder methods, iterators and generators, context managers, and the runtime including the GIL and memory management, plus quick-fire Q&A and a cheat sheet.',
 'Free', '/python-guide.html', 'Yes');

INSERT INTO KTS_COURSES (C_ID, C_TITLE, C_DESC, C_PRICE, C_LINK, C_TRENDING) VALUES
('KTS015',
 'Microservices - Complete Guide',
 'An in-depth, plain-English microservices guide covering monolith vs microservices, when to use them, how to split by business capability, synchronous vs asynchronous communication, API gateway and service discovery, database per service, the Saga pattern and compensating transactions, the outbox pattern and eventual consistency, CQRS and event sourcing, resilience patterns like timeouts, retries, circuit breakers and idempotency, observability with tracing, and deployment with containers and Kubernetes, plus quick-fire Q&A and a cheat sheet.',
 'Free', '/microservices-guide.html', 'Yes');

INSERT INTO KTS_COURSES (C_ID, C_TITLE, C_DESC, C_PRICE, C_LINK, C_TRENDING) VALUES
('KTS016',
 'System Design - Complete Guide',
 'An in-depth, plain-English system design guide covering the interview framework and back-of-envelope estimation, vertical vs horizontal scaling, load balancing and stateless services, caching and CDNs, SQL vs NoSQL, replication and sharding, the CAP theorem and consistency models, message queues, reliability and availability nines, key latency numbers, and a worked URL-shortener example, plus quick-fire Q&A and a cheat sheet.',
 'Free', '/system-design-guide.html', 'Yes');

COMMIT;
