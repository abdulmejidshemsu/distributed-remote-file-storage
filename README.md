# Distributed Remote File Storage

## Using Sockets and Remote Method Invocation

[![Architecture](https://i.ibb.co/Xzt6fLq/1520119173997.jpg)](https://i.ibb.co)

## Features

- Upload files
- Delete files
- Download files
- Search files
- Distribute files across data nodes

## Tech

- [Java] - for programming the applicaiton!
- [MYSQL] - for database
- [Netbeans] - for designing GUI part.

## Installation

Install the dependencies and devDependencies and start the server.

For starting name node

```sh
cd name_node/source_code/NameNode/server
java RemoteFileStorageServer
cd name_node/source_code/NameNode/client/view
java RemoteFileStorageView
```

For starting data nodes

```sh
cd data_nodes\data_node_<number>\source_code\DataNode_<number>\src\datanode_<number>
java DataNode_<number>
```
