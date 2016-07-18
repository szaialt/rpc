RPC, version 3
==============

Instead of exchanging fixed messages, call a remote computation on the
server side, defined by an interface.

The interface description is not used directly by the client in this
version, and it contains only one operation to call. The interface
describes a simple calculator service which can add two integer numbers.

The service invocation is done by receiving the operands, instantiating
the implementation, calling the method and sending the result back.
