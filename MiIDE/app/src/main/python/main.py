# MiIDE Python 运行时入口（Chaquopy）
#
# 提供 execute() 给 Android 侧调用：接收 base64 编码的代码，
# 在隔离的 stdout/stderr 中执行并返回 JSON 结果。

import base64
import io
import json
import sys
import traceback


def execute(b64code):
    """执行一段 base64 编码的 Python 代码，返回 JSON 字符串。"""
    try:
        code = base64.b64decode(b64code).decode("utf-8")
    except Exception as e:
        return json.dumps({"stdout": "", "stderr": "代码解码失败: %s" % e})

    old_out, old_err = sys.stdout, sys.stderr
    buf_out, buf_err = io.StringIO(), io.StringIO()
    sys.stdout, sys.stderr = buf_out, buf_err
    try:
        exec(compile(code, "<miide>", "exec"), {})
    except BaseException:
        traceback.print_exc(file=buf_err)
    finally:
        sys.stdout, sys.stderr = old_out, old_err

    return json.dumps({"stdout": buf_out.getvalue(), "stderr": buf_err.getvalue()})
