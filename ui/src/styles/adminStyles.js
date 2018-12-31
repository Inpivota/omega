

const drawerWidth = 240;

export const adminStyles = theme => ({
    root: {
        display: 'flex',
    },
    appBar: {
        zIndex: theme.zIndex.drawer + 1,
    },
    drawer: {
        width: drawerWidth,
        flexShrink: 0,
    },
    drawerPaper: {
        width: drawerWidth,
    },
    content: {
        flexGrow: 1,
        padding: theme.spacing.unit * 3,
    },
    toolbar: theme.mixins.toolbar,
    appBarButton: {
        color: theme.palette.primary.contrastText,
    },
    pageWrapper: {
        marginLeft: drawerWidth,
        marginTop: theme.spacing.unit * 8,
    },
});