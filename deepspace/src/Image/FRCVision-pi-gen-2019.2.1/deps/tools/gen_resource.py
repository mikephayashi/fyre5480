#!/usr/bin/env python3

import argparse
import os
import re

parser = argparse.ArgumentParser(description='Generate resource .cpp file.')
parser.add_argument('outputFile', help='output file')
parser.add_argument('inputFile', help='input file')
parser.add_argument('--prefix', dest='prefix', default='', help='C function prefix')
parser.add_argument('--namespace', dest='namespace', default='', help='C++ namespace')

args = parser.parse_args()

with open(args.inputFile, "rb") as f:
    data = f.read()

fileSize = len(data)

inputBase = os.path.basename(args.inputFile)
funcName = "GetResource_" + re.sub(r"[^a-zA-Z0-9]", "_", inputBase)

with open(args.outputFile, "wt") as f:
    print("#include <stddef.h>\n#include <wpi/StringRef.h>\nextern \"C\" {\nstatic const unsigned char contents[] = { ", file=f, end='')
    print(", ".join("0x%02x" % x for x in data), file=f, end='')
    print(" };", file=f)
    print("const unsigned char* {}{}(size_t* len) {{\n  *len = {};\n  return contents;\n}}\n}}".format(args.prefix, funcName, fileSize), file=f)

    if args.namespace:
        print("namespace {} {{".format(namespace), file=f)
    print("wpi::StringRef {}() {{\n  return wpi::StringRef(reinterpret_cast<const char*>(contents), {});\n}}".format(funcName, fileSize), file=f)
    if args.namespace:
        print("}", file=f)
